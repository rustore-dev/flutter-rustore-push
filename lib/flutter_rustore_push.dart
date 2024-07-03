import 'package:flutter_rustore_push/pigeons/rustore_push.dart';

class RustorePushCallbacks extends RuStorePushCallbacks {
  RustorePushCallbacks({
    this.onDeletedMessages,
    this.onError,
    this.onMessageReceived,
    this.onNewToken,
  });

  Function? onDeletedMessages;
  Function? onError;
  Function? onMessageReceived;
  Function? onNewToken;

  @override
  Future<void> deletedMessages() async {
    if (onDeletedMessages != null) {
      onDeletedMessages!();
    }
  }

  @override
  Future<void> error(String err) async {
    if (onError != null) {
      onError!(err);
    }
  }

  @override
  Future<void> messageReceived(Message message) async {
    if (onMessageReceived != null) {
      onMessageReceived!(message);
    }
  }

  @override
  Future<void> newToken(String token) async {
    if (onNewToken != null) {
      onNewToken!(token);
    }
  }
}

class RustorePushClient {
  static final _api = RuStorePush();

  static void setup() {}

  static Future<void> attachCallbacks({
    Function? onDeletedMessages,
    Function? onError,
    Function? onMessageReceived,
    Function? onNewToken,
  }) async {
    RuStorePushCallbacks.setup(RustorePushCallbacks(
      onDeletedMessages: onDeletedMessages,
      onError: onError,
      onMessageReceived: onMessageReceived,
      onNewToken: onNewToken,
    ));

    return;
  }

  static Future<bool> available() async {
    return _api.available();
  }

  static Future<String> getToken() async {
    return _api.getToken();
  }

  static Future<void> deleteToken() async {
    return _api.deleteToken();
  }

  static Future<void> subscibeToTopic(String topicName) async {
    return _api.subscribeToTopic(topicName);
  }

  static Future<void> unsubscribeFromTopic(String topicName) async {
    return _api.unsubscribeFromTopic(topicName);
  }
}
