# zammad-android
Modern android client library for Zammad

[![](https://jitpack.io/v/KirkBushman/zammad-android.svg)](https://jitpack.io/#KirkBushman/zammad-android)

### Info

zammad-android is a [Zammad](https://github.com/zammad/zammad) client that target the android platform specifically.
It is built with Kotlin, Retrofit, Moshi, OkHttp.

zammad-android it's hosted on Jitpack

### How to install.

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {

    implementation 'com.github.KirkBushman:zammad-android:**Version**'
}
```

### Obtain a Client.

```kotlin
    val client = ZammadClient(
        baseurl = "**Zammad Url (https:// included)**",
        username = "**Your Username**", 
        password = "**Your Password**", 
        logging = true
    )
```

### Use the Client.

```kotlin

    // Quick cookbook

    // fetch account infos
    val me: User = client.me()
    println(me.toString())

    // fetch groups
    val groups: List<Group> = client.groups()
    groups.forEach {
        println(it.toString())    
    }

    // fetch tickets
    val tickets: List<Ticket> = client.tickets()
    tickets.forEach {
         println(it.toString())
    }

    // create a new ticket
    client.createTicket(
        title = "**Title**",
        groupId = // some group,
        stateId = // some state,
        priorityId = // some priority,
        ownerId = // some owner,
        customerId = // some customer,
        subject = "**Subject**",
        body = "**Body**",
        type = "note",
        internal = false
    )

    // fetch notifications
    val notifications: List<OnlineNotification> = client.onlineNotifications(true)
    notifications.forEach {
        println(it.toString())
    }

    val overviews: List<Overview> = client.overviews()
    overviews.forEach {
        println(it.toString())
    }
```

Look into the sample app for more.

### License
This project is licensed under the MIT License
