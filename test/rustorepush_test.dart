import 'package:flutter_rustore_push/pigeons/rustore.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockClientPlatform with MockPlatformInterfaceMixin implements Client {
  @override
  Future<String> initialize(String arg_project) {
    // TODO: implement initialize
    throw UnimplementedError();
  }

  @override
  Future<void> onDeletedMessages() {
    // TODO: implement onDeletedMessages
    throw UnimplementedError();
  }

  @override
  Future<String> onError() {
    // TODO: implement onError
    throw UnimplementedError();
  }

  @override
  Future<Message> onMessageReceived() {
    // TODO: implement onMessageReceived
    throw UnimplementedError();
  }

  @override
  Future<String> onNewToken() {
    // TODO: implement onNewToken
    throw UnimplementedError();
  }
}

void main() {
  test('getPlatformVersion', () async {});
}
