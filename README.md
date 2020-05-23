# twitter-bot-vue-3
Twitter bot announcing new versions of Vue 3 related repositories

## Schedule Event
[Create rule](https://docs.aws.amazon.com/AmazonCloudWatch/latest/events/ScheduledEvents.html)
```
aws events put-rule --schedule-expression "rate(5 minutes)" --name FiveMinRule
```
[Add permission]()
```
aws lambda add-permission --function-name twitter-bot-vue-3 --action lambda:InvokeFunction --principal events.amazonaws.com --s
ource-arn <rule-arn-from-above> --statement-id my-scheduled-event
```
[Get Lambda function arn]()
```
aws lambda list-functions
```
[Put target]()
```
aws events put-targets --rule FiveMinRule --targets "Id"="1","Arn"="<function-arn>"
```

## DynamoDB AWS
[Create table](https://awscli.amazonaws.com/v2/documentation/api/latest/reference/dynamodb/create-table.html):
```
aws dynamodb create-table --table-name twitter-bot-vue-3 --attribute-definitions AttributeName=Project,AttributeType=S -
-key-schema AttributeName=Project,KeyType=HASH --billing-mode PAY_PER_REQUEST
```

[Add project](https://awscli.amazonaws.com/v2/documentation/api/latest/reference/dynamodb/put-item.html):
```
aws dynamodb put-item --table-name twitter-bot-vue-3 --item "{\"Project\": {\"S\": \"Vue 3\"}, \"Changelog\": {\"S\": \"
https://raw.githubusercontent.com/vuejs/vue-next/master/CHANGELOG.md\"}, \"LastRecordedChange\": {\"S\": \"...\"}}"
```

## AWS Secrets Manager
```
aws secretsmanager create-secret --name twitter-bot-vue-3/auth --description "Twitter app keys and tokens" --secret-string "{\"consumer-key\":\"API key\",\"consumer-secret\":\"API secret key\",\"access-token\":\"Access token\",\"access-token-secret\":\"Access token secret\"}"
```

## Twitter
Apply for [developer account](https://developer.twitter.com/en/apply/user.html) and select "Making a bot"