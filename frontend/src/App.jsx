import { useMemo, useState } from "react";
import "./App.css";

const API_BASE = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/api";

function App() {
  const [authForm, setAuthForm] = useState({
    usuario: "",
    contrasena: "",
  });
  const [sesion, setSesion] = useState(null);
  const [estado, setEstado] = useState({ tipo: "", mensaje: "" });

  const titulo = useMemo(() => (sesion ? "Bienvenido" : "Iniciar sesión"), [sesion]);

  const onAuthInput = (event) => {
    const { name, value } = event.target;
    setAuthForm((prev) => ({ ...prev, [name]: value }));
  };

  const mostrarEstado = (tipo, mensaje) => setEstado({ tipo, mensaje });

  const enviarLogin = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch(`${API_BASE}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          usuario: authForm.usuario,
          contrasena: authForm.contrasena,
        }),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.mensaje || "Credenciales incorrectas");
      }

      setSesion(data);
      mostrarEstado("ok", data.mensaje || "Login correcto");
    } catch (error) {
      mostrarEstado("error", error.message);
    }
  };

  const cerrarSesion = () => {
    setSesion(null);
    setAuthForm({ usuario: "", contrasena: "" });
    mostrarEstado("", "");
  };

  return (
    <main className="app">
      <header className="header">
        <h1>Spring Boot — ejemplo</h1>
        <p>Login contra API Spring Boot + MySQL</p>
      </header>

      {estado.mensaje && (
        <p className={`estado ${estado.tipo === "error" ? "error" : "ok"}`}>
          {estado.mensaje}
        </p>
      )}

      {!sesion ? (
        <section className="card">
          <h2>{titulo}</h2>
          <form onSubmit={enviarLogin} className="grid">
            <input
              name="usuario"
              placeholder="Usuario"
              value={authForm.usuario}
              onChange={onAuthInput}
              autoComplete="username"
              required
            />
            <input
              name="contrasena"
              type="password"
              placeholder="Contraseña"
              value={authForm.contrasena}
              onChange={onAuthInput}
              autoComplete="current-password"
              required
            />
            <button type="submit">Entrar</button>
          </form>
        </section>
      ) : (
        <section className="card hola">
          <h2>Hola mundo</h2>
          <p className="sub">
            Sesión: <strong>{sesion.usuario}</strong>
          </p>
          <button type="button" className="secondary" onClick={cerrarSesion}>
            Cerrar sesión
          </button>
        </section>
      )}
    </main>
  );
}

export default App;
