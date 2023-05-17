package com.example.vetrazcenter.presentation.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vetrazcenter.R
import com.example.vetrazcenter.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCall()
        setupOpenMapListeners()

    }

    private fun setupOpenMapListeners() {
        binding.btnOpenMapMainOffice.setOnClickListener {
            val coordinates = context?.getString(R.string.main_office_coordinates)
            if (coordinates != null) {
                openMap(coordinates)
            }
        }
        binding.btnOpenMapOffice2.setOnClickListener {
            val coordinates = context?.getString(R.string.office_2_coordinates)
            if (coordinates != null) {
                openMap(coordinates)
            }
        }
        binding.btnOpenMapEcolog.setOnClickListener {
            val coordinates = context?.getString(R.string.ecolog_coordinates)
            if (coordinates != null) {
                openMap(coordinates)
            }
        }
        binding.btnOpenMapSputnik.setOnClickListener {
            val coordinates = context?.getString(R.string.sputnic_coordinates)
            if (coordinates != null) {
                openMap(coordinates)
            }
        }
    }

    private fun setupCall() {
        binding.tvPhoneMainOffice.setOnClickListener {
            val number = binding.tvPhoneMainOffice.text.toString()
            call(number)
        }
        binding.tvPhoneEcolog.setOnClickListener {
            val number = binding.tvPhoneEcolog.text.toString()
            call(number)
        }
        binding.tvPhoneDirector.setOnClickListener {
            val number = binding.tvPhoneDirector.text.toString()
            call(number)
        }
        binding.tvPhoneOffice2.setOnClickListener {
            val number = binding.tvPhoneOffice2.text.toString()
            call(number)
        }
        binding.tvPhoneSputnik.setOnClickListener {
            val number = binding.tvPhoneSputnik.text.toString()
            call(number)
        }
        binding.tvPhoneReseption.setOnClickListener {
            val number = binding.tvPhoneReseption.text.toString()
            call(number)
        }
    }

    private fun call(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }

    private fun openMap(coordinates: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("geo:$coordinates")
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}