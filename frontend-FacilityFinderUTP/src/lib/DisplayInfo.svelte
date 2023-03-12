<script>
    import {informacionClase} from '../store'
</script>
<div class=" bg-violet-600 py-2 px-4 my-4 mx-auto max-w-xl rounded-lg">
    <p class=" text-slate-50 text-lg font-mono">INFORMACIÓN:</p>
    <div class=" bg-slate-50 p-4">
        <table class=" mx-auto w-full font-mono border-separate border-spacing-y-1 border-spacing-x-2">
            <tbody class=" w-full">
                <tr>
                    <td class=" font-bold">Horario</td>
                    <td>{$informacionClase.horario}</td>
                </tr>
                <tr class=" bg-slate-300">
                    <td class=" font-bold">Aula</td>
                    <td>{$informacionClase.aula}</td>
                </tr>
                <tr>
                    <td class=" font-bold">Torre</td>
                    <td>{$informacionClase.torre}</td>
                </tr>
                <tr>
                    <td class=" font-bold">Profesor</td>
                    <td>{$informacionClase.profesor}</td>
                </tr>
                <tr>
                    <td class=" font-bold">Curso</td>
                    <td>{$informacionClase.curso}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>