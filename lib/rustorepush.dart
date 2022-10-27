import 'package:rustorepush/pigeons/rustore.dart';

class RustorePushClient {
  var _api = PushClient();

  Future<String> initialize(String project) async {
    return _api.initialize(project);
  }
}
