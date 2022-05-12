package com.tp_apps.helpers

object Constants {

    object BaseURL {
        //private const val BASE_API = "http://10.0.2.2:5000"
        private const val BASE_API = "https://api.andromia.science"
        const val TICKETS_URL = "https://api.andromia.science/tickets"
        const val TICKETS = "${BASE_API}/tickets"
        const val CUSTOMERS = "${BASE_API}/customers"
        const val GATEWAYS = "${BASE_API}/gateways"
        const val NETWORK = "${BASE_API}/network"

    }

    const val GATEWAY_REFRESH = 60000L
    const val GATEWAY_LOADING = 1000L
    const val TIMER_MAX = 10000L
    const val TIMER_INTERVAL = 1000L
    const val REFRESH_TICKET_DELAY = 30000L
    const val REFRESH_NETWORK_DELAY = 120000L

    const val FLAG_API_URL = "https://flagcdn.com/h40/%s.png"

    enum class TicketPriority {
        Low, Normal, High, Critical
    }

    enum class TicketStatus {
        Open, Solved
    }

    enum class ConnectionStatus {
        Online, Offline
    }

}