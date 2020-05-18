# twitter-bot-vue-3
Twitter bot announcing new versions of Vue 3 related repositories

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