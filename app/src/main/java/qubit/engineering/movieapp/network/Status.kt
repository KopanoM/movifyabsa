package qubit.engineering.movieapp.network

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class CheckNetwork(val status:Status, message: String){

    companion object{
        val LOADED: CheckNetwork
        val LOADING: CheckNetwork
        val ERROR: CheckNetwork

        init {
            LOADED = CheckNetwork(Status.SUCCESS,"Success")
            LOADING = CheckNetwork(Status.RUNNING,"Running")
            ERROR = CheckNetwork(Status.FAILED,"Something went wrong")
        }
    }
}