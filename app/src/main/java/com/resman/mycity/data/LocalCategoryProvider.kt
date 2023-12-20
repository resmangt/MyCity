package com.resman.mycity.data

import com.resman.mycity.R
import com.resman.mycity.model.Category
import com.resman.mycity.model.Site

object LocalCategoryDataProvider {
    val defaultCategory = getCategory()[0]
    fun getCategory(): List<Category> {
        return listOf(
            Category(
                id = 1,
                title = R.string.restaurants,
                imageResourceId = R.drawable.restaurante_futurista,
                sites = 4,
                listSites = listOf(
                    Site(
                        imageResourceId = R.drawable.estrella_del_amanecer_gourmet,
                        title = R.string.estrella_del_amanecer,
                        reviews = 4.5,
                    ),
                    Site(
                        imageResourceId = R.drawable.saz_n_del_mar_azul,
                        title = R.string.sazón_del_mar,
                        reviews = 4.2,
                    ),
                    Site(
                        imageResourceId = R.drawable.la_cocina_de_la_luna,
                        title = R.string.la_cocina_de_la_luna,
                        reviews = 4.0,
                    ),
                    Site(
                        imageResourceId = R.drawable.bocado_de_para_so,
                        title = R.string.bocado_de_paraiso,
                        reviews = 4.5,
                    )
                )
            ),
            Category(
                id = 2,
                title = R.string.hotels,
                imageResourceId = R.drawable.hotel_futurista,
                sites = 5,
                listSites = listOf(
                    Site(
                        imageResourceId = R.drawable.aurora_elegance_resort,
                        title = R.string.aurora_elegance_resort,
                        reviews = 4.2,
                    ),
                    Site(
                        imageResourceId = R.drawable.villa_celestial_vista,
                        title = R.string.villa_celestial_vista,
                        reviews = 4.1
                    ),
                    Site(
                        imageResourceId = R.drawable.el_refugio_de_los_sue_os,
                        title = R.string.el_refugio_de_los_sueños,
                        reviews = 4.6,
                    ),
                    Site(
                        imageResourceId = R.drawable.palacio_n_mada_boutique,
                        title = R.string.palacio_nomada_boutique,
                        reviews = 4.5,
                    ),
                    Site(
                        imageResourceId = R.drawable.jardines_del_ed_n_retreat,
                        title = R.string.jardines_del_eden,
                        reviews = 4.5,
                    )
                )
            ),
            Category(
                id = 3,
                title = R.string.museums,
                imageResourceId = R.drawable.museo_futurista,
                sites = 3,
                listSites = listOf(
                    Site(
                        imageResourceId = R.drawable.galer_a_cosmos_de_las_artes,
                        title = R.string.museo_galeria_cosmos_de_las_artes,
                        reviews = 4.2,
                    ),
                    Site(
                        imageResourceId = R.drawable.museo_de_la_herencia_del_tiempo,
                        title = R.string.museo_de_la_herencia,
                        reviews = 4.1
                    ),
                    Site(
                        imageResourceId = R.drawable.santuario_de_historia_y_ciencia,
                        title = R.string.santuario_de_historia,
                        reviews = 4.6,
                    )
                )
            ),
            Category(
                id = 4,
                title = R.string.malls,
                imageResourceId = R.drawable.centro_comercial_futurista,
                sites = 3,
                listSites = listOf(
                    Site(
                        imageResourceId = R.drawable.maxresdefault,
                        title = R.string.megamall,
                        reviews = 4.6,
                    ),
                    Site(
                        imageResourceId = R.drawable.jardines_del_futuro_shopping_center,
                        title = R.string.el_oasis_de_la_ciudad,
                        reviews = 4.4
                    ),
                    Site(
                        imageResourceId = R.drawable.avenida_estelar_mall,
                        title = R.string.plaza_del_sol,
                        reviews = 4.5,
                    )
                )
            ),
            Category(
                id = 5,
                title = R.string.pharmacy,
                imageResourceId = R.drawable.farmacia_futurista,
                sites = 4,
                listSites = listOf(
                    Site(
                        imageResourceId = R.drawable.farmacia_vida_verde,
                        title = R.string.farmacia_vida_verde,
                        reviews = 4.2,
                    ),
                    Site(
                        imageResourceId = R.drawable.botica_del_alivio,
                        title = R.string.botica_del_alivio,
                        reviews = 4.1
                    ),
                    Site(
                        imageResourceId = R.drawable.salud_eterna_farmacia,
                        title = R.string.farmacia_salud_eterna,
                        reviews = 4.6,
                    ),
                    Site(
                        imageResourceId = R.drawable.cuidado_y_bienestar_botiqu_n,
                        title = R.string.cuidado_y_bienestar,
                        reviews = 4.5,
                    )
                )
            )
        )
    }
}