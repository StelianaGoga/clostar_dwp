function allowedInputChars(event, regexp) {
	var charCode = null;
	if (typeof event.charCode != undefined && event.charCode > 0) {
		charCode = event.charCode;
	} else if (event.keyCode > 0){
		charCode = event.keyCode;
	}
	if (charCode == null) {
		return false;
	}

	/* enter, bs, tab */
	if (charCode == 13 || charCode == 8 || charCode == 9) {	
		return true;
	}

	var iChar = String.fromCharCode(charCode);
	if (charCode > 96 && charCode < 123) {
//		event.keyCode = charCode - 32;
		iChar = String.fromCharCode(charCode - 32);
	}

	if (iChar.search(regexp) == 0) {
		return false;
	}

	return true;
}
