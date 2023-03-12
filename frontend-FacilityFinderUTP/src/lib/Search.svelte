<script>
    import botonBusqueda from '../assets/busqueda.png'
</script>
<form class=" font-semibold bg-slate-100 p-1 w-min mx-auto my-3 border border-slate-800 rounded-lg flex">
    <select name="" id="" class="rounded-l-lg">
        <option value="U">U</option>
        <option value="C">C</option>
    </select>
    <input type="number" name="codigo" class=" p-2 rounded-r-lg" placeholder=00000000>
    <button class=" bg-red-400 p-1 my-auto mx-1 w-8 h-8 rounded-lg hover:bg-red-600"><img src={botonBusqueda} alt="buscar" class=" "></button>
</form>