//let replyservice =(function (){})() ; 함수기본형태
let replyservice =(function (){


    let list = function (page, callback) {
        $.getJSON("/replies/list/" + bno,{page : page} , function (data){

            callback(data)

        })

    }

    let register = function (reply, callback) {
        console.log("진입")
        $.post({
            url :"/replies/new",
            data : JSON.stringify(reply), //json문자열
            contentType : "application/json; charset=utf-8"

        },function (data) {
            callback(data) //콜백함수로 콘솔창에 data가 출력
        });
    }

    let modify = function (reply, callback) {
        $.ajax({
            url: "/replies/modify/" + reply.rno,
            data: JSON.stringify(reply),
            type: 'put',
            contentType: 'application/json; charset=utf-8',
            success: function (result) {
                callback(result)
            },
            err: function (result, status, error) {
                console.log(result.status + "" + result.error)
            }
        });

    }

    let read = function (rno, callback) {
        $.get("/replies/get/" + rno, function (data) {
            callback(data)
        });
    }

    let remove = function (rno, callback){
        $.ajax({

            url : "/replies/remove/" + rno,
            type: 'delete' ,
            dataType : 'json',
            success: function (data) {
                callback(data)
            },
            error: function (result, status, error) {
                alert(result.status + " " + result.error)
            },
        })
    }

    return{
        list : list,
        register : register,
        modify : modify,
        read : read,
        delete : remove

    }

})() ;





