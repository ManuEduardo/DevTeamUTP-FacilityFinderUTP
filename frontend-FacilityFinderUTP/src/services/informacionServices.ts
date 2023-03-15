export const getInformacionClaseFetch = async (
  tipoCodigo: string,
  codigo: number
) => {
  if (tipoCodigo == "U") {
    const codigoCompleto = `U${codigo}`;
    const clase = await fetch(
      `http://localhost:8000/estudiante?codigo=${codigoCompleto}`
    ).then((response) => response.json());
    console.log(clase)
    const infoClase = {
      horario: clase.horario,
      aula: `${clase.pabellon} ${clase.piso} ${clase.aula}`,
      torre: clase.torre,
      profesor: clase.profesor,
      curso: clase.curso,
    };
    return infoClase;
  }
  const codigoCompleto = `C${codigo}`;
  const clase = await fetch(
    `http://localhost:8000/profesor?codigo=${codigoCompleto}`
  ).then((response) => response.json());
  const infoClase = {
    horario: clase.horario,
    aula: `${clase.pabellon} ${clase.piso} ${clase.aula}`,
    torre: clase.torre,
    profesor: clase.profesor,
    curso: clase.curso,
  };
  return infoClase;
};
