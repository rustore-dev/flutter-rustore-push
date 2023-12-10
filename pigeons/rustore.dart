import 'package:pigeon/pigeon.dart';

@ConfigurePigeon(PigeonOptions(
  dartOut: 'lib/pigeons/rustore_push.dart',
  dartOptions: DartOptions(),
  kotlinOut: 'android/src/main/kotlin/ru/rustore/flutter_rustore_push/pigeons/RuStorePush.kt',
  kotlinOptions: KotlinOptions(
    package: 'ru.rustore.flutter_rustore_push.pigeons',
  ),
  dartPackageName: 'flutter_rustore_push',
))
class Message {
  String? messageId;
  late int priority;
  late int ttl;
  late String? collapseKey;
  late Map<String?, String?> data;
  late Notification? notification;
}

class Notification {
  String? title;
  String? body;
  String? channelId;
  String? imageUrl;
  String? color;
  String? icon;
  String? clickAction;
}

class ClientId {
  String? type;
  String? id;
}

@HostApi()
abstract class RuStorePush {
  @async
  void initialization(String project, ClientId? client);

  @async
  bool available();

  @async
  String getToken();

  @async
  void deleteToken();
}

@FlutterApi()
abstract class RuStorePushCallbacks {
  @async
  void newToken(String token);

  @async
  void messageReceived(Message message);

  @async
  void deletedMessages();

  @async
  void error(String error);
}
