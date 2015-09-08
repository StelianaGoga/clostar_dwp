function show_pic_details(id, src) {
	var imgElem = document.getElementById(id);
	imgElem.src = src;
	var elem = document.getElementById("picture_" + id);
	//elem.style.display='block';
	elem.style.visibility = 'visible';
}

function hid_pic_details(id, src) {
	var imgElem = document.getElementById(id);
	imgElem.src = src;
	var elem = document.getElementById("picture_" + id);
	//elem.style.display='none';
	elem.style.visibility = 'hidden';
}

function change_big_pic(pic) {
	var big_pic = document.getElementById("big_pic");
	big_pic.src = pic;
}