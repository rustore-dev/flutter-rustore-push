import 'package:pigeon/pigeon.dart';

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

@HostApi()
abstract class Client {
  @async
  bool available();

  @async
  String initialize(String project);

  @async
  String onNewToken();

  @async
  Message onMessageReceived();

  @async
  void onDeletedMessages();

  @async
  String onError();

  @async
  String getToken();

  @async
  void deleteToken();
}
