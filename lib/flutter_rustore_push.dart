import 'package:flutter_rustore_push/pigeons/rustore.dart';

typedef void Callback(dynamic value);

class RustorePushClient {
  static final _api = Client();

  static Future<bool> available() async {
    return _api.available();
  }

  static onNewToken(Callback success, {Callback? error}) async {
    try {
      var resp = await _api.onNewToken();
      success(resp);
    } catch (err) {
      if (error != null) {
        error(err);
      }
    }
    onNewToken(success, error: error);
  }

  static onMessageReceived(Callback success, {Callback? error}) async {
    try {
      var resp = await _api.onMessageReceived();
      success(resp);
    } catch (err) {
      if (error != null) {
        error(err);
      }
    }
    onMessageReceived(success, error: error);
  }

  static onDeletedMessages(Callback success, {Callback? error}) async {
    try {
      await _api.onDeletedMessages();
      success(null);
    } catch (err) {
      if (error != null) {
        error(err);
      }
    }
    onDeletedMessages(success, error: error);
  }

  static onError(Function success, {Function? error}) async {
    try {
      var resp = await _api.onError();
      success(resp);
    } catch (err) {
      if (error != null) {
        error(err);
      }
    }
    onError(success, error: error);
  }

  static Future<String> getToken() async {
    await Future.delayed(Duration(seconds: 1));
    return _api.getToken();
  }

  static Future<void> deleteToken() async {
    return _api.deleteToken();
  }
}
