<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="euc-kr">
<title>Canvas Paint Example</title>
</head>
<script type="text/javascript">

   if (window.addEventListener) {
      window.addEventListener('load', InitEvent, false);
   }
   var canvas, context, tool;
   function InitEvent() {
      canvas = document.getElementById('drawCanvas');
      if (!canvas) {
         alert("캔버스 객체를 찾을 수 없음");
         return;
      }
      if (!canvas.getContext) {
         alert("Drawing Contextf를 찾을 수 없음");
         return;
      }
      context = canvas.getContext('2d');
      if (!context) {
         alert("getContext() 함수를 호출 할 수 없음");
         return;
      }

      // Pencil tool 객체를 생성 한다.
      tool = new tool_pencil();

      canvas.addEventListener('mousedown', ev_canvas, false);
      canvas.addEventListener('mousemove', ev_canvas, false);
      canvas.addEventListener('mouseup', ev_canvas, false);
   }
   function tool_pencil() {
      var tool = this;
      this.started = false;

      // 마우스를 누르는 순간 그리기 작업을 시작 한다. 
      this.mousedown = function(ev) {
         context.beginPath();
         context.moveTo(ev._x, ev._y);
         tool.started = true;
      };

      // 마우스가 이동하는 동안 계속 호출하여 Canvas에 Line을 그려 나간다
      this.mousemove = function(ev) {
         if (tool.started) {
            context.lineTo(ev._x, ev._y);
            context.stroke();
         }
      };
      // 마우스 떼면 그리기 작업을 중단한다
      this.mouseup = function(ev) {
         if (tool.started) {
            tool.mousemove(ev);
            tool.started = false;
         }
      };
   }
   // Canvas요소 내의 좌표를 결정 한다.
   function ev_canvas(ev) {
      if (ev.layerX || ev.layerX == 0) { // Firefox 브라우저
         ev._x = ev.layerX;
         ev._y = ev.layerY;
      } else if (ev.offsetX || ev.offsetX == 0) { // Opera 브라우저
         ev._x = ev.offsetX;
         ev._y = ev.offsetY;
      }
      // tool의 이벤트 핸들러를 호출한다.
      var func = tool[ev.type];
      if (func) {
         func(ev);
      }
   }
     // 지우기
        function clearCanvas()
         {
        context.clearRect(0,0,canvas.width,canvas.height);

        }

</script>
<body>

 <button id="btnDel" onclick="clearCanvas()">eraser</button>
   <form id="board">
      <div id="container">
         <canvas id="drawCanvas" width="700" height="800"
            style="position: fixed; border: 1px solid #000;"></canvas>
      </div>
   </form>
   
</body>
</html>