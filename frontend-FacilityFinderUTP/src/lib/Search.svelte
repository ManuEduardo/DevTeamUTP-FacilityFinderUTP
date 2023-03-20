<script lang="ts">
  import botonBusqueda from "../assets/busqueda.png";
  import {codigo, } from '../store'
  export let getInformacionClase;
</script>
<form
  class=" font-semibold bg-slate-100 p-1 w-min h-min mx-auto my-auto border border-slate-800 rounded-lg flex"
>
  <input
    type="text"
    name="codigo"
    class=" w-32 p-2 rounded-r-lg "
    placeholder="U00000000"
    bind:value={$codigo}
  />
  <button
    class=" bg-red-400 p-1 my-auto mx-1 w-8 h-8 rounded-lg hover:bg-red-600"
    on:click={getInformacionClase}
    ><img src={botonBusqueda} alt="buscar" /></button
  >
</form>
