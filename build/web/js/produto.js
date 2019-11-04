/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* caixa de texto escondida com os ids das categorias selecionadas */
var inputCategorias = document.getElementById("inputCategorias");
/* combobox de categorias para permitir a seleção e adição de categorias */
var selectCategoria = document.getElementById("selectCategoria");
/* div que exibe as categorias selecionadas */
var listCategorias = document.getElementById("listCategorias");

/* vetor de objetos de categorias selecionadas */
var selectedCategorias = [];

/* função que faz a adição de uma categoria selecionada no combobox */
function adicionarCategoria() {
    if (selectCategoria.selectedIndex <= 0)
        return;
    var categoria = {
        id: selectCategoria.options[selectCategoria.selectedIndex].value,
        descricao: selectCategoria.options[selectCategoria.selectedIndex].text
    };
    var achou = false;
    for (var i = 0; i < selectedCategorias.length; i++) {
        if (selectedCategorias[i].id == categoria.id) {
            achou = true;
            break;
        }
    }
    if (!achou) {
        selectedCategorias[selectedCategorias.length] = categoria;
        atualizarListaCategorias();
    }
}

/* função para remover uma categoria que foi adicionada */
function removerCategoria(id) {
    var novoSelectedCategorias = [];
    var achou = false;
    for (var i = 0; i < selectedCategorias.length; i++) {
        if (selectedCategorias[i].id == id) {
            achou = true;
        } else {
            novoSelectedCategorias[novoSelectedCategorias.length] = selectedCategorias[i];
        }
    }
    if (achou) {
        selectedCategorias = novoSelectedCategorias;
        atualizarListaCategorias();
    }
}

/* função que faz a atualização das categorias adicionadas, observando o vetor de objetos de categorias selecioadas */
function atualizarListaCategorias() {
    inputCategorias.value = "";
    listCategorias.innerHTML = "";
    if (selectedCategorias.length > 0) {
        var html = "";
        html += "<table>";
        html += "<thead>";
        html += "<tr>";
        html += "<th>Id</th>";
        html += "<th>Descricao</th>";
        html += "<th>&nbsp;</th>";
        html += "</tr>";
        html += "</thead>";
        html += "<tbody>";
        for (var i = 0; i < selectedCategorias.length; i++) {
            var c = selectedCategorias[i];
            html += "<tr>";
            html += "<td>" + c.id + "</td>";
            html += "<td>" + c.descricao + "</td>";
            html += "<td><input type=\"button\" onclick=\"removerCategoria(" + c.id + ");\" value=\"Remover\" /></td>";
            html += "</tr>";
            inputCategorias.value += c.id;
            if (i < selectedCategorias.length - 1)
                inputCategorias.value += ";";
        }
        html += "</tbody>";
        html += "</table>";
        listCategorias.innerHTML = html;
    }
}

function obterCategoria(id) {
    if (selectCategoria.options.length > 0) {
        for (var i = 0; i < selectCategoria.options.length; i++) {
            if (parseInt(selectCategoria.options[i].value, 10) == id) {
                var categoria = {
                    id: selectCategoria.options[i].value,
                    descricao: selectCategoria.options[i].text
                };
                return categoria;
            }
        }
    }
    return null;
}

if (inputCategorias.value.length > 0) {
    var categoriasId = [];
    if (inputCategorias.value.indexOf(";") == -1) {
        categoriasId[categoriasId.length] = inputCategorias.value;
    } else {
        categoriasId = inputCategorias.value.split(";");
    }
    for (var i = 0; i < categoriasId.length; i++) {
        selectedCategorias[i] = obterCategoria(categoriasId[i]);
    }
    atualizarListaCategorias();
}