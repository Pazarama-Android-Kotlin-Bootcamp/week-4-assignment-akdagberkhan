package com.akdagberkhan.weatherapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.akdagberkhan.weatherapp.R


class DataFragment : Fragment() {


    lateinit var btnSearch : Button
    lateinit var etLat : EditText
    lateinit var etLon : EditText
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        btnSearch = view.findViewById(R.id.btnSearch)
        etLat = view.findViewById(R.id.etLat)
        etLon = view.findViewById(R.id.etLon)

        btnSearch.setOnClickListener {
            setData()
        }
    }

    private fun setData()
    {
        navController.navigate(R.id.action_dataFragment_to_detailFragment, Bundle().apply {
            putString("lat", etLat.text.toString());
            putString("lon", etLon.text.toString())
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false)
    }


}