import 'package:flutter_rustore_push/pigeons/rustore.dart';

class RustorePushClient {
  static final _api = Client();

  static Future<bool> available() async {
    return _api.available();
  }

  static Future<String> initialize(String project) async {
    return _api.initialize(project);
  }

  static Future<String> onNewToken() async {
    return _api.onNewToken();
  }

  static Future<Message> onMessageReceived() async {
    return _api.onMessageReceived();
  }

  static Future<void> onDeletedMessages() async {
    return _api.onDeletedMessages();
  }

  static Future<String> onError() async {
    return _api.onError();
  }

  static Future<String> getToken() async {
    return _api.getToken();
  }

  static Future<void> deleteToken() async {
    return _api.deleteToken();
  }
}
