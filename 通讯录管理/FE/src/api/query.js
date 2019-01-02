import { SERVER_BASE, http } from "./config";

export default {
  search() {
    return http.get(SERVER_BASE + "/search/all");
  },
  searchbyid(id) {
    return http.get(SERVER_BASE + "/search/one" + "?id=" + id);
  },
  sort() {
    return http.get(SERVER_BASE + "/sort");
  },
  deletebyid(id) {
    return http.get(SERVER_BASE + "/delete/name&tel" + "?id="+id);
  },
  insertinfo(name, tel) {
    return http.get(SERVER_BASE + "/insert/name&tel" + "?name="+name+"&tel="+tel);
  }
};
