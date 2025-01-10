# Dağıtık Abonelik Sistemi (Distributed Subscriber Service)

## Proje Hakkında
Bu proje, Java, Ruby ve Python dillerini kullanarak bir dağıtık sistem uygulamasını hayata geçirmektedir. Sistem, sunucular arasında iletişim sağlayarak veri akışını yönetir ve belirli görevleri yerine getirir. Projede yer alan Google Protocol Buffers (Protobuf) ise farklı programlama dilleri arasında veri alışverişini daha verimli ve kolay hale getirmektedir.

## Kurulum ve Çalıştırma Adımları

### 1. dist_servers Klasöründe Protobuf Dosyalarını Derleme
Protobuf dosyalarını derlemek için aşağıdaki komutları kullanın:
```sh
protoc --java_out=. Capacity.proto
protoc --java_out=. Subscriber.proto
```

### 2. dist_servers Klasöründe Sunucuları Derleme
Server.java dosyalarını derlemek için aşağıdaki komutu çalıştırın:
```sh
javac -cp ".;com/google/protobuf/protobuf-java-4.29.1.jar" *.java
```

### 3. dist_servers Klasöründe Sunucuları Çalıştırma
Sunucuları sırayla çalıştırmak için aşağıdaki komutları kullanın:
```sh
java -cp ".;com/google/protobuf/protobuf-java-4.29.1.jar" Server1
java -cp ".;com/google/protobuf/protobuf-java-4.29.1.jar" Server2
java -cp ".;com/google/protobuf/protobuf-java-4.29.1.jar" Server3
```

### 4. panel Klasöründe Ruby Dosyalarını Derleme
Ruby için Protobuf dosyalarını derlemek için şu komutu çalıştırın:
```sh
protoc --ruby_out=. Capacity.proto
```

### 5. panel Klasöründe Ruby Scriptini Çalıştırma
admin.rb dosyasını çalıştırmak için şu komutu kullanın:
```sh
ruby admin.rb
```

### 6. plotting Klasöründe Protobuf Dosyalarını Derleme
Python için Protobuf dosyalarını derlemek için aşağıdaki komutu kullanın:
```sh
protoc --python_out=. Capacity.proto
```

### 7. plotting Klasöründe Python Scriptini Çalıştırma
plotter.py dosyasını çalıştırmak için şu komutu kullanın:
```sh
python plotter.py
```

### 8. Clients Klasöründe Protobuf Dosyalarını Derleme
Clients klasöründeki Protobuf dosyalarını derlemek için şu komutu çalıştırın:
```sh
protoc --java_out=. Subscriber.proto
```

### 9. Clients Klasöründe Java Dosyalarını Derleme
Client.java dosyalarını derlemek için şu komutu çalıştırın:
```sh
javac -cp ".;com/google/protobuf/protobuf-java-4.29.1.jar" *.java
```

### 10. Clients Klasöründe Java Dosyalarını Sırasıyla çalıştırma
Client.java dosyalarını çalıştırmak için şu komutları çalıştırın:
```sh
java -cp ".;com/google/protobuf/protobuf-java-4.29.1.jar" SubscriptionClient
java -cp ".;com/google/protobuf/protobuf-java-4.29.1.jar" SubscriptionClient1
java -cp ".;com/google/protobuf/protobuf-java-4.29.1.jar" SubscriptionClient2
```

## Tamamlanan Gorevler:
- **Ruby Scripti (admin.rb):**
  - Ruby scripti admin.rb, Server.java dosyalarıyla bağlantı kurabiliyor.
  - Ayrıca, admin.rb ile Python scripti plotter.py arasında başarılı bir bağlantı kurulmuştur.

- **Server.java İletişimi:**
  - Sunucular, birbirleriyle etkili bir şekilde iletişim kurarak abone bilgilerini başarıyla paylaşabiliyor ve yedekleme işlemlerini sorunsuz bir şekilde gerçekleştirebiliyor.

### Ekip üyeleri :
-Muhammed Emir Cinan (22060328) 
-İsmail Enes Öztürk (22060383)
-Mehmet Er (22060327)

### Sunum Videosu Linki :
https://www.youtube.com/watch?v=sTMA-Rm_tvw


