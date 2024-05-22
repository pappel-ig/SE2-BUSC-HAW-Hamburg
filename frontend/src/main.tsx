import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./main.css";
import axios from 'axios';

if (import.meta.env.DEV) axios.defaults.baseURL = 'http://localhost:8080'
else axios.defaults.baseURL = 'http://mensa-match.web.informatik.haw-hamburg.de:8080'

axios.defaults.withCredentials = true;

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <App/>
  </React.StrictMode>
);
