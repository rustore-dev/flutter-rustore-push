import 'package:rustorepush/pigeons/rustore.dart';

class RustorePushClient {
  var _api = PushClient();

  void initialize(){
    _api.initialize();
  }
}
