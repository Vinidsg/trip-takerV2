var url = new URL(window.location);

document.getElementById("local").innerText = url.searchParams.get("local");
document.getElementById("local").value = url.searchParams.get("local");

document.getElementById("guiaResponsavel").innerText = url.searchParams.get("guiaResponsavel");
document.getElementById("guiaResponsavel").value = url.searchParams.get("guiaResponsavel");

document.getElementById("descricao").innerText = url.searchParams.get("descricao");
