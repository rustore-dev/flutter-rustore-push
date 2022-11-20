import 'package:rustorepush/pigeons/rustore.dart';

class RustorePushClient {
  var _api = PushClient();

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
