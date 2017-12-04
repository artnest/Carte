package by.carte.restaurants.data

import by.carte.restaurants.data.local.DbHelper
import by.carte.restaurants.data.remote.ApiHelper

interface DataManager : DbHelper, ApiHelper {
}
