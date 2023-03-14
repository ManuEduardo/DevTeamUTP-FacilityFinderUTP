<script lang="ts">
  import BodyApp from "./lib/Body.svelte";
  import logoUtp from "./assets/logo_texto.png";
</script>

<main>
  <div class=" min-h-screen flex flex-col justify-between">
    <div>
      <div class=" h-2 bg-violet-700" />
      <a href="https://www.utp.edu.pe/" target="_blank" rel="noreferrer" class=" m-3" >
        <img src={logoUtp} alt="logo" />
      </a>
    </div>
    <BodyApp />
    <div class=" h-2 bg-violet-700" />
  </div>
</main>

<style>
</style>
