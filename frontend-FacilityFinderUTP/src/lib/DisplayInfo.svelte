<script>
  import { informacionClase } from "../store";
  import loadingGif from "../assets/load.gif";
  export let loading;
</script>

<div class=" bg-violet-600 py-2 px-4 pb-4 my-4 mx-auto max-w-xl rounded-lg">
  <p class=" text-slate-50 text-lg font-mono m-1">INFORMACIÓN:</p>
  <div class=" bg-slate-50 p-4 rounded-md">
    {#if loading}
      <div class=" h-64">
        <img src={loadingGif} alt="cargando" class=" w-full max-w-xs mx-auto" />
      </div>
    {:else}
      <table
        class=" mx-auto w-full font-mono border-separate border-spacing-y-1"
      >
        <tbody class=" w-full">
          <tr>
            <td class=" font-bold p-2">Dia</td>
            <td class=" text-right p-2">{$informacionClase.dia}</td>
          </tr>
          <tr>
            <td class=" font-bold p-2">Alumno</td>
            <td class=" text-right p-2">{$informacionClase.alumno}</td>
          </tr>
          <tr>
            <td class=" font-bold p-2">Horario</td>
            <td class=" text-right p-2">{$informacionClase.horario}</td>
          </tr>
          <tr>
            <td class=" font-bold p-2">Sede</td>
            <td class=" text-right p-2">{$informacionClase.sede}</td>
          </tr>
          <tr>
            <td class=" font-bold bg-slate-300 p-2 rounded-l-lg">Aula</td>
            <td
              class=" text-right text-lg font-bold bg-slate-300  rounded-r-lg p-2"
              >{$informacionClase.aula}</td
            >
          </tr>
          <tr>
            <td class=" font-bold p-2">Torre</td>
            <td class=" text-right p-2">{$informacionClase.torre}</td>
          </tr>
          <tr>
            <td class=" font-bold p-2">Profesor</td>
            <td class=" text-right p-2">{$informacionClase.profesor}</td>
          </tr>
          <tr>
            <td class=" font-bold p-2">Curso</td>
            <td class=" text-right p-2">{$informacionClase.curso}</td>
          </tr>
        </tbody>
      </table>
    {/if}
  </div>
</div>
