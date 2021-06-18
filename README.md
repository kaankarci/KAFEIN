# KAFEIN

Alınan hata;
UserController içerisinde yorum satırı haline getirmiş olduğum;
updateUser ve deleteUser hata veriyor. Gelen hata;

java: cannot find symbol
  symbol:   method build()
  location: class org.springframework.http.ResponseEntity<com.example.restservice.model.User>




#API 

user/ 			->(GET) tum kullanıcılar

------------------------------------------------

user/1			->(GET) id'si 1 olanı getirir

------------------------------------------------

user/			->(POST) kullanıcı ekler	
											
------------------------------------------------
Ornek:
{
"id":5,
"isim":"postman1",
"soyisim":"postman1",
"kullaniciTipi":"admin",
"username":"postman1234",
"password":"postman1234"
}



