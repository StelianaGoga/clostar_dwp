function show_pic_details(id, src) {
	var imgElem = document.getElementById(id);
	imgElem.src = src;
	var elem = document.getElementById("picture_" + id);
	elem.style.display='block';
}
function hid_pic_details(id, src){
	var imgElem = document.getElementById(id);
	imgElem.src = src;
	var elem = document.getElementById("picture_" + id);
	elem.style.display='none';
}