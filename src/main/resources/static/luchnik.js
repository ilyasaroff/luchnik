$(document).ready(function(){

        $.get("/status", "", processNom);


    var numClick = 0;
    var last = 0;
    var next = 0;
    $('.div').click(function() {
        numClick++;
        if (numClick==1) last = $(this).attr('id');
        if (numClick==2) {
            next = $(this).attr('id');
            numClick=0;
            $.get("/step/"+last+"/"+next, "", processNom);
        }
        $(this).css('border', '1px solid red');
//        alert(clickId);
      });

      function processNom(data) {
              for (var i=1; i<26; i++) {
                  $("#"+i).html("");
                  $("#"+i).css('border', '0px');
              }
              var pl1 = data.player1;
              var pl2 = data.player2;
              $("#mess").prepend(data.mess+'<br/>');
              //alert (pl1[0][0]);
              for (var i=0; i<5; i++) {
                  $("#"+pl1[i][0]).html("<img src='jpg/red.png'>");
                  $("#"+pl2[i][0]).html("<img src='jpg/black.png'>");
                  }
              }
      $('#startgame').click(function() {
      $.get("/start");
      $.get("/status", "", processNom);
      return false;
      });

});