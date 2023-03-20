  import { writable } from "svelte/store";
  import { EmptyInformacionClase } from "./models";

  export const informacionClase = writable(EmptyInformacionClase)
  export const codigo= writable("");
