<script lang="ts">
  import flechaAbajo from "../assets/flecha_abajo.png";
  import muñeco from "../assets/imagen_muñeco.png"
  import Search from "./Search.svelte";
  import DisplayInfo from "./DisplayInfo.svelte";
  import {EmptyInformacionClase} from '../models';
  import {getInformacionClaseFetch} from '../services/informacionServices'
  import {codigo,  informacionClase} from '../store'

  let loading = false;

  const getInformacionClase = async (event:Event) =>{
    event.preventDefault();
    if($codigo == "" ){
      alert("Rellena todos los datos")
      return;
    }
    if($codigo[0].toUpperCase() != "U"){
      alert("Código invalido")
      return;
    }
    loading = true;
    $informacionClase = EmptyInformacionClase;
    const info = await getInformacionClaseFetch($codigo).finally(()=>loading = false);
    $informacionClase = info
  }


</script>

<div class=" bg-slate-300 m-4 p-3">
  <h1
    class=" text-center text-3xl font-medium text-slate-800 font-mono mb-4"
  >
    Buscador de Instalaciones - UTP
  </h1>
  <div class=" w-max mx-auto ">
    <div class="py-1 px-2 font-mono text-center tracking-tighter font-medium bg-emerald-500/60">
      ¿No recuerdas tu salón?
    </div>
    <img src={flechaAbajo} alt="flecha" class=" mt-2 mx-auto "/>
  </div>
  <div class=" flex max-w-sm mx-auto">
    <Search getInformacionClase={getInformacionClase}/>
    <img src={muñeco} alt="imagen Muñeco" class=" w-24 mx-auto">
  </div>
  <DisplayInfo loading={loading}/>
  <div>
    <p class=" text-center mt-12 mx-auto text-sm w-80">
      ¿No ha sido de ayuda? comunicate con SAE <a href="tel:+(0801)19600">(0801)19600</a>
    </p>
  </div>
</div>
