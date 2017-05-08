package com.denysnovoa.nzbmanager.radarr.movie.release.view.model

data class MovieReleaseViewModel(val title: String,
                                 val size: Double,
                                 val indexerId: Int,
                                 val indexer: String,
                                 val rejected: Boolean,
                                 val downloadAllowed: Boolean,
                                 val age: Int,
                                 val seeders: Int,
                                 val leechers: Int,
                                 val guid: String,
                                 val downloadUrl: String,
                                 val infoUrl: String)