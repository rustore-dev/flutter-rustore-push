import 'package:flutter/material.dart';
import 'package:flutter_rustore_push/flutter_rustore_push.dart';
import 'package:http/http.dart' as http;

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

    RustorePushClient.onNewToken((value) {
      final item = "on new token success: ${value}";

      print(item);

      setState(() {
        stack.add(item);
        token = value;
      });
    }, error: (err) {
      final item = "on new token err: ${err}";

      print(item);

      setState(() {
        stack.add(item);
      });
    });

    RustorePushClient.onMessageReceived((value) {
      final item = "on message received success: id=${value.messageId}, data=${value.data}, notification.body: ${value.notification?.body}";

      print(item);

      setState(() {
        stack.add(item);
      });
    }, error: (err) {
      final item = "on message received error: ${err}";

      print(item);

      setState(() {
        stack.add(item);
      });
    });

    RustorePushClient.onError((value) {
      final item = "on error: ${value}";

      print(item);

      setState(() {
        stack.add(item);
      });
    });
  }

  var project = "jYqD02VNCyrXKvlyLv3sCwCPkjlFCvqy";
  var bearer = "kVmEIcP93JXJzO-GFFn9MZ0JSqwuRSfzfNA5XOF130PwI8htgHSxyHZ0Pn3b00ea";

  void send(String token) {
    final body = """{
   "message":{
      "token": "${token}",
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
      Uri.parse('https://vkpns.rustore.ru/v1/projects/${project}/messages:send'),
      headers: {
        'Authorization': 'Bearer ${bearer}',
      },
      body: body,
    )
        .then((resp) {
      print(resp.statusCode);
      print(resp.body);
      print(resp);
    });
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
