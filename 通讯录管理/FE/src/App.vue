<template>
  <div id="app">
    <address-book></address-book>
  </div>
</template>

<script>
import AddressBook from "./components/addressBook";
export default {
  name: 'App',
  components: {
    AddressBook

  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
