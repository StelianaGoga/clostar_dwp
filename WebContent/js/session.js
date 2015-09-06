function make_option_elem(value, text) {
	var option = document.createElement("option");
	option.text = text;
	option.value = value;
	return option;
}

function make_shoes_optgroup(){

    var optgrp = document.createElement("optgroup");
    optgrp.setAttribute("label", "Shoes");
    optgrp.setAttribute("id", "shoes_list_id");
    var option = make_option_elem(13, "Shoes");
    optgrp.appendChild(option);
    
    option = make_option_elem(14, "Sandals");
    optgrp.appendChild(option);
    
    option = make_option_elem(15, "Moccasins");
    optgrp.appendChild(option);
    
    option = make_option_elem(16, "Sport ShoeS");
    optgrp.appendChild(option);
    
    option = make_option_elem(17, "Boots");
    optgrp.appendChild(option);
    
	return optgrp;
}

function onSelectGender(){
	var gender = document.getElementById("gender_dropbox").value;
	var type = document.getElementById("item_type_dropbox");
	type.options.length = 0;
	if (gender == 1 || gender == 4) {
		type.add(make_option_elem(0, "Dresses"));
		type.add(make_option_elem(1, "Jackets"));
		type.add(make_option_elem(2, "Jeans"));
		type.add(make_option_elem(3, "Tops"));
		type.add(make_option_elem(4, "Skirts"));
		type.add(make_option_elem(5, "Shirts"));
		type.add(make_option_elem(6, "T-Shirts"));
		type.add(make_option_elem(7, "Trousers"));
		type.add(make_option_elem(8, "Coats"));
		
		if (document.getElementById("shoes_list_id") != null)
			type.removeChild(document.getElementById("shoes_list_id"));
		type.appendChild(make_shoes_optgroup());
	}
	else if (gender == 2) {
		type.add(make_option_elem(1, "Jackets"));
		type.add(make_option_elem(2, "Jeans"));
		type.add(make_option_elem(5, "Shirts"));
		type.add(make_option_elem(6, "T-Shirts"));
		type.add(make_option_elem(7, "Trousers"));
		type.add(make_option_elem(8, "Coats"));
		
		if (document.getElementById("shoes_list_id") != null)
			type.removeChild(document.getElementById("shoes_list_id"));
		type.appendChild(make_shoes_optgroup());
	}
	else if (gender == 3) {
		type.add(make_option_elem(1, "Jackets"));
		type.add(make_option_elem(3, "Tops"));
		type.add(make_option_elem(11, "Bottoms"));
		type.add(make_option_elem(12, "Full costumes"));
		type.add(make_option_elem(9, "Shoes"));

		if (document.getElementById("shoes_list_id") != null)
			type.removeChild(document.getElementById("shoes_list_id"));
	}
	else {
		if (document.getElementById("shoes_list_id") != null)
			type.removeChild(document.getElementById("shoes_list_id"));
	}
}

function onChangeMeasuresNo(){
	var measuresDiv = document.getElementById("measuresDiv");
	var measureDiv_1 = document.getElementById("measureDiv_1");
	var measuresNo = document.getElementById("measuresNo").value;

	while(true) {
		var lastChild = measuresDiv.lastElementChild;
		var lastChildNo = parseInt(lastChild.id.substr(lastChild.id.indexOf("_") + 1));
		if (lastChildNo > measuresNo && lastChildNo != 1) {
			measuresDiv.removeChild(lastChild);
		}
		else if (lastChildNo == 1 && measuresNo == 0){
			lastChild.style.display = 'none';
			lastChild.getElementsByTagName("input")[0].value = "";
			lastChild.getElementsByTagName("input")[1].value = ""; 
			break;
		}
		else {
			break;
		}
	}
	
	for(var i = 1; i <= measuresNo; i++) {
		if (document.getElementById("measureDiv_" + i) != null) {
			document.getElementById("measureDiv_" + i).style.display = 'block';
			continue;
		}
		var newDiv = document.createElement("div");
		newDiv = measureDiv_1.cloneNode(true);
		newDiv.setAttribute('id',"measureDiv_" + i);
		newDiv.getElementsByTagName("input")[0].value = "";
		newDiv.getElementsByTagName("input")[1].value = ""; 
		measuresDiv.appendChild(newDiv);
	}
}

function onChangeMeasureQuantity(){
	return true;
}