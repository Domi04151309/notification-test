# Notification Test

This is related to a bug report.

## How to use

Enable the notification listener service using the first entry in the list.
After that notification icons should get loaded, however if the icon type is `TYPE_RESSOURCE` the loading will fail.
When the `QUERY_ALL_PACKAGES` permission gets added in the manifest the icons can get loaded without a problem.
