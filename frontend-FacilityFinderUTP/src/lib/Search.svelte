<script lang="ts">
  import botonBusqueda from "../assets/busqueda.png";
  import {codigo, tipoCodigo, } from '../store'
  export let getInformacionClase;
</script>
<form
  class=" font-semibold bg-slate-100 p-1 w-min mx-auto my-3 border border-slate-800 rounded-lg flex"
>
  <select name="" id="" class="rounded-l-lg" bind:value={$tipoCodigo}>
    <option value="U">U</option>
    <option value="C">C</option>
  </select>
  <input
    type="number"
    name="codigo"
    class=" p-2 rounded-r-lg"
    placeholder="00000000"
    bind:value={$codigo}
  />
  <button
    class=" bg-red-400 p-1 my-auto mx-1 w-8 h-8 rounded-lg hover:bg-red-600"
    on:click={getInformacionClase}
    ><img src={botonBusqueda} alt="buscar" /></button
  >
</form>
