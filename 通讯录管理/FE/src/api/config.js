import axios from "axios";

const SERVER_BASE = "http://127.0.0.1:8001";
let http = axios.create();
http.interceptors.request.use(
  config => {
    if (sessionStorage.$token) {
      config.headers = { Authorization: sessionStorage.$token };
    }
    return config;
  },
  // eslint-disable-next-line handle-callback-err
  err => {
    return Promise.reject(error);
  }
);

export { SERVER_BASE, http };
