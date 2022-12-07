import 'package:flutter_rustore_push/pigeons/rustore.dart';

class RustorePushClient {
  final _api = Client();

  Future<String> initialize(String project) async {
    return _api.initialize(project);
  }

  Future<String> onNewToken() async {
    return _api.onNewToken();
  }

  Future<Message> onMessageReceived() async {
    return _api.onMessageReceived();
  }

  Future<void> onDeletedMessages() async {
    return _api.onDeletedMessages();
  }

  Future<String> onError() async {
    return _api.onError();
  }
}
