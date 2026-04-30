import { useEffect, useMemo, useState } from "react";
import "./App.css";

const API_BASE = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/api";

const initialVacacion = {
  pais: "",
  lugarTuristico: "",
  descripcion: "",
};

function App() {
  const [modoAuth, setModoAuth] = useState("login");
  const [authForm, setAuthForm] = useState({
    nombreUsuario: "",
    contrasena: "",
    email: "",
  });
  const [usuario, setUsuario] = useState(null);
  const [vacaciones, setVacaciones] = useState([]);
  const [vacacionForm, setVacacionForm] = useState(initialVacacion);
  const [vacacionEditandoId, setVacacionEditandoId] = useState(null);
  const [estado, setEstado] = useState({ tipo: "", mensaje: "" });

  const tituloAuth = useMemo(
    () => (modoAuth === "login" ? "Iniciar sesion" : "Registrar cuenta"),
    [modoAuth]
  );

  const onAuthInput = (event) => {
    const { name, value } = event.target;
    setAuthForm((prev) => ({ ...prev, [name]: value }));
  };

  const onVacacionInput = (event) => {
    const { name, value } = event.target;
    setVacacionForm((prev) => ({ ...prev, [name]: value }));
  };

  const mostrarEstado = (tipo, mensaje) => setEstado({ tipo, mensaje });

  const cargarVacaciones = async (usuarioId) => {
    try {
      const response = await fetch(`${API_BASE}/vacaciones/usuario/${usuarioId}`);
      if (!response.ok) {
        throw new Error("No se pudieron cargar las vacaciones");
      }
      const data = await response.json();
      setVacaciones(data);
    } catch (error) {
      mostrarEstado("error", error.message);
    }
  };

  useEffect(() => {
    if (usuario?.usuarioId) {
      cargarVacaciones(usuario.usuarioId);
    }
  }, [usuario]);

  const enviarAuth = async (event) => {
    event.preventDefault();

    const endpoint = modoAuth === "login" ? "login" : "registrar";
    const payload =
      modoAuth === "login"
        ? {
            nombreUsuario: authForm.nombreUsuario,
            contrasena: authForm.contrasena,
          }
        : {
            nombreUsuario: authForm.nombreUsuario,
            contrasena: authForm.contrasena,
            email: authForm.email,
          };

    try {
      const response = await fetch(`${API_BASE}/auth/${endpoint}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.mensaje || "Error al autenticar");
      }

      if (modoAuth === "login") {
        setUsuario(data);
        mostrarEstado("ok", `Bienvenido ${data.nombreUsuario}`);
      } else {
        mostrarEstado("ok", "Usuario registrado. Ahora inicia sesion.");
        setModoAuth("login");
      }
    } catch (error) {
      mostrarEstado("error", error.message);
    }
  };

  const limpiarVacacionForm = () => {
    setVacacionForm(initialVacacion);
    setVacacionEditandoId(null);
  };

  const guardarVacacion = async (event) => {
    event.preventDefault();
    if (!usuario?.usuarioId) return;

    const editando = Boolean(vacacionEditandoId);
    const url = editando
      ? `${API_BASE}/vacaciones/${vacacionEditandoId}`
      : `${API_BASE}/vacaciones/usuario/${usuario.usuarioId}`;

    try {
      const response = await fetch(url, {
        method: editando ? "PUT" : "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(vacacionForm),
      });

      if (!response.ok) {
        const mensaje = await response.text();
        throw new Error(mensaje || "No se pudo guardar la vacacion");
      }

      mostrarEstado("ok", editando ? "Vacacion actualizada" : "Vacacion creada");
      limpiarVacacionForm();
      await cargarVacaciones(usuario.usuarioId);
    } catch (error) {
      mostrarEstado("error", error.message);
    }
  };

  const editarVacacion = (vacacion) => {
    setVacacionEditandoId(vacacion.id);
    setVacacionForm({
      pais: vacacion.pais,
      lugarTuristico: vacacion.lugarTuristico,
      descripcion: vacacion.descripcion || "",
    });
  };

  const eliminarVacacion = async (id) => {
    if (!usuario?.usuarioId) return;

    try {
      const response = await fetch(`${API_BASE}/vacaciones/${id}`, {
        method: "DELETE",
      });

      if (!response.ok) {
        throw new Error("No se pudo eliminar la vacacion");
      }

      mostrarEstado("ok", "Vacacion eliminada");
      await cargarVacaciones(usuario.usuarioId);
    } catch (error) {
      mostrarEstado("error", error.message);
    }
  };

  const cerrarSesion = () => {
    setUsuario(null);
    setVacaciones([]);
    setAuthForm({ nombreUsuario: "", contrasena: "", email: "" });
    limpiarVacacionForm();
    mostrarEstado("", "");
  };

  return (
    <main className="app">
      <header className="header">
        <h1>Dream Vacation</h1>
        <p>Frontend React conectado a la API Spring Boot</p>
      </header>

      {estado.mensaje && (
        <p className={`estado ${estado.tipo === "error" ? "error" : "ok"}`}>
          {estado.mensaje}
        </p>
      )}

      {!usuario ? (
        <section className="card">
          <h2>{tituloAuth}</h2>
          <form onSubmit={enviarAuth} className="grid">
            <input
              name="nombreUsuario"
              placeholder="Nombre de usuario"
              value={authForm.nombreUsuario}
              onChange={onAuthInput}
              required
            />
            <input
              name="contrasena"
              type="password"
              placeholder="Contrasena"
              value={authForm.contrasena}
              onChange={onAuthInput}
              required
            />
            {modoAuth === "registro" && (
              <input
                name="email"
                type="email"
                placeholder="Email"
                value={authForm.email}
                onChange={onAuthInput}
                required
              />
            )}
            <button type="submit">{modoAuth === "login" ? "Entrar" : "Crear cuenta"}</button>
          </form>
          <button
            type="button"
            className="secondary"
            onClick={() => setModoAuth(modoAuth === "login" ? "registro" : "login")}
          >
            {modoAuth === "login" ? "No tienes cuenta? Registrate" : "Ya tienes cuenta? Inicia sesion"}
          </button>
        </section>
      ) : (
        <>
          <section className="card">
            <div className="row-between">
              <h2>Hola, {usuario.nombreUsuario}</h2>
              <button type="button" className="secondary" onClick={cerrarSesion}>
                Cerrar sesion
              </button>
            </div>

            <form onSubmit={guardarVacacion} className="grid">
              <input name="pais" placeholder="Pais" value={vacacionForm.pais} onChange={onVacacionInput} required />
              <input
                name="lugarTuristico"
                placeholder="Lugar turistico"
                value={vacacionForm.lugarTuristico}
                onChange={onVacacionInput}
                required
              />
              <textarea
                name="descripcion"
                placeholder="Descripcion"
                value={vacacionForm.descripcion}
                onChange={onVacacionInput}
              />
              <button type="submit">{vacacionEditandoId ? "Actualizar vacacion" : "Guardar vacacion"}</button>
              {vacacionEditandoId && (
                <button type="button" className="secondary" onClick={limpiarVacacionForm}>
                  Cancelar edicion
                </button>
              )}
            </form>
          </section>

          <section className="card">
            <h2>Mis vacaciones ({vacaciones.length})</h2>
            {vacaciones.length === 0 ? (
              <p>No hay vacaciones registradas.</p>
            ) : (
              <ul className="lista">
                {vacaciones.map((vacacion) => (
                  <li key={vacacion.id}>
                    <div>
                      <strong>
                        {vacacion.pais} - Capital: {vacacion.ciudad}
                      </strong>
                      <p>{vacacion.lugarTuristico}</p>
                      <p>Poblacion actual: {(vacacion.poblacionActual || 0).toLocaleString()}</p>
                      {vacacion.descripcion && <small>{vacacion.descripcion}</small>}
                    </div>
                    <div className="acciones">
                      <button type="button" className="secondary" onClick={() => editarVacacion(vacacion)}>
                        Editar
                      </button>
                      <button type="button" className="danger" onClick={() => eliminarVacacion(vacacion.id)}>
                        Eliminar
                      </button>
                    </div>
                  </li>
                ))}
              </ul>
            )}
          </section>
        </>
      )}
    </main>
  );
}

export default App;
