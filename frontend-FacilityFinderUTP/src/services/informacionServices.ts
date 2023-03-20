export const getInformacionClaseFetch = async (codigo: string) => {
  const clase = await fetch(
    `http://localhost:8000/estudiante?codigo=${codigo.toUpperCase()}`
  ).then((response) => response.json());
  if (clase.pabellon == "no encontrado") {
    clase.pabellon = "";
    clase.aula = "";
  }
  const infoClase = {
    horario: clase.horario,
    aula: `${clase.pabellon} ${clase.piso} ${clase.aula}`,
    torre: clase.torre,
    profesor: clase.profesor,
    curso: clase.curso,
    dia: clase.dia,
    sede: clase.sede,
  };
  return infoClase;
  // const clase = await fetch(
  //   `http://localhost:8000/profesor?codigo=${codigo}`
  // ).then((response) => response.json());
  // if(clase.pabellon == "no encontrado"){
  //   clase.pabellon = ""
  //   clase.aula = ""
  // }
  // const infoClase = {
  //   horario: clase.horario,
  //   aula: `${clase.pabellon} ${clase.piso} ${clase.aula}`,
  //   torre: clase.torre,
  //   profesor: clase.profesor,
  //   curso: clase.curso,
  //   dia: clase.dia,
  //   sede: clase.sede,
  // };
  // return infoClase;
};
