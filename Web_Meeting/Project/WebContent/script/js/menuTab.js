	function getChildElementNodes(parentNode){
		var childNodes = parentNode.childNodes;
		var childArray = new Array();
		for(var i=0; i<childNodes.length; i++){
			if(childNodes[i].nodeType == 1){
				childArray.push(childNodes[i]);
			}
		}
		return childArray;
	}

	function toggleImg(imgNode, onOff){
		var imgFileName = imgNode.src;
		if(onOff == "on"){
			if(imgFileName.lastIndexOf("_ov.gif") < 0){
				imgNode.src = imgFileName.substring(0, imgFileName.lastIndexOf(".gif")) + "_ov.gif";
			}
		}else{
			if(imgFileName.lastIndexOf("_ov.gif") > 0){
				imgNode.src = imgFileName.substring(0, imgFileName.lastIndexOf(".gif")-3) + ".gif";
			}
		}
	}

	function changeImage(targetNode){
		var allNodes = getChildElementNodes(targetNode.parentNode);
		for(var i=0; i<allNodes.length; i++){
			var img = allNodes[i].getElementsByTagName("img")[0];
			if(img != undefined){
				if(allNodes[i] == targetNode){
					toggleImg(img, "on");
				}else{
					toggleImg(img, "off");
				}
			}
		}
	}

	function showContent(targetNode){
		var allNodes = getChildElementNodes(targetNode.parentNode);
		for(var i=0; i<allNodes.length; i++){
			var content = allNodes[i].getElementsByTagName("ul")[0];
			if(content != undefined){
				if(allNodes[i] == targetNode){
					content.style.display = "block";
				}else{
					content.style.display = "none";
				}
			}
		}
	}

	function initMain(){
		var navigation = document.getElementById("navigation");
		var mainMenuList = getChildElementNodes(navigation);
		for(var i=0; i<mainMenuList.length; i++){
			var a = mainMenuList[i].getElementsByTagName("a")[0];
			a.onmouseover = function(){
				changeImage(this.parentNode);
				showContent(this.parentNode);
			}
			a.onfocus = function(){
				changeImage(this.parentNode);
				showContent(this.parentNode);
			}
		}

		
		var boardBox = document.getElementById("boardBox");
		var boardBoxList = boardBox.getElementsByTagName("h3");	
		for(var i=0; i<boardBoxList.length; i++){
			boardBoxList[i].onclick = function(){
				changeImage(this);
				showContent(getElementNextSibling(this));
			}
		}
		showContent(getElementNextSibling(boardBoxList[0]));
	}

	function getElementNextSibling(node){
		var nextElement = node.nextSibling;
		if(nextElement == null){
			return null;
		}else if(nextElement.nodeType != 1){
			return getElementNextSibling(nextElement);
		}
		return nextElement;
	}



