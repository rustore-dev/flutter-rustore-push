import 'package:flutter/material.dart';
import 'package:flutter_rustore_push/flutter_rustore_push.dart';
import 'package:http/http.dart' as http;
import 'package:permission_handler/permission_handler.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  List<String> stack = [];
  String token = "";

  @override
  void initState() {
    super.initState();

    initPush();
  }

  void initPush() {
    RustorePushClient.attachCallbacks(onNewToken: (value) {
      final item = "on new token success: ${value}";

      print(item);

      setState(() {
        stack.add(item);
        token = value;
      });
    }, onMessageReceived: (value) {
      final item =
          "on message received success: id=${value.messageId}, data=${value.data}, notification.body: ${value.notification?.body}";

      print(item);

      setState(() {
        stack.add(item);
      });
    }, onDeletedMessages: () {
      print("on delete message");
    }, onError: (value) {
      final item = "on error: ${value}";

      print(item);

      setState(() {
        stack.add(item);
      });
    }, onMessageOpenedApp: (value) {
      final item = "onMessageOpenedApp success: ${value.notification?.title}";
      print(item);

      setState(() {
        stack.add(item);
      });
    });

    RustorePushClient.getToken().then((value) {
      final item = "get token success: ${value}";

      print(item);

      setState(() {
        stack.add(item);
        token = value;
      });
    });

    RustorePushClient.available().then((value) {
      final item = "available success: ${value}";

      print(item);

      setState(() {
        stack.add(item);
      });
    }, onError: (err) {
      final item = "available error: ${err}";

      print(item);

      setState(() {
        stack.add(item);
      });
    });

    RustorePushClient.getInitialMessage().then((value) {
      print("getInitialMessage from dart called: ${value?.notification?.title}");
    }, onError: (err) {
      print("getInitialMessage from dart error: $err");
    });
  }

  var project = "projectID";
  var serviceToken =
      "CubILkB-PWdxO8vWrFQ3WKjPKqSWQoeDMStTdU-T5PIOYKyMAt-0w3EWE2tjNOD7";

  void send(String deviceToken) async {
    if (await Permission.notification.isGranted) {
      final body = """{
   "message":{
      "token": "$deviceToken",
      "notification":{
        "body":"This is an rustore notification!",
        "title":"Message",
        "image":"https://image-hosting.org/284239234.jpeg"
      }
   }
}""";

      print(body);

      http
          .post(
        Uri.parse(
            'https://vkpns.rustore.ru/v1/projects/${project}/messages:send'),
        headers: {
          'Authorization': 'Bearer $serviceToken',
        },
        body: body,
      )
          .then((resp) {
        print(resp.statusCode);
        print(resp.body);
        print(resp);
      });
    } else {
      Permission.notification.request();
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('RuStore PushSDK Example'),
        ),
        body: SingleChildScrollView(
          child: Padding(
            padding: EdgeInsets.symmetric(horizontal: 24),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                ElevatedButton(
                  onPressed: () {
                    RustorePushClient.getToken().then((value) {
                      final item = "get token success: ${value}";

                      print(item);

                      setState(() {
                        stack.add(item);
                        token = value;
                      });
                    }, onError: (err) {
                      final item = "get token error: ${err}";

                      print(item);

                      setState(() {
                        stack.add(item);
                      });
                    });
                  },
                  child: const Text("Get token"),
                ),
                ElevatedButton(
                  onPressed: () {
                    RustorePushClient.deleteToken().then((value) {
                      final item = "delete token success";

                      print(item);

                      setState(() {
                        stack.add(item);
                        token = "";
                      });
                    }, onError: (err) {
                      final item = "delete token error: ${err}";

                      print(item);

                      setState(() {
                        stack.add(item);
                      });
                    });
                  },
                  child: const Text("Delete token"),
                ),
                Text("token: ${token}"),
                ElevatedButton(
                  onPressed: () {
                    send(token);
                  },
                  child: const Text("Send"),
                ),
                SizedBox(height: 8),
                for (final item in stack) ...[
                  Text(item),
                  SizedBox(height: 4),
                ],
              ],
            ),
          ),
        ),
      ),
    );
  }
}
