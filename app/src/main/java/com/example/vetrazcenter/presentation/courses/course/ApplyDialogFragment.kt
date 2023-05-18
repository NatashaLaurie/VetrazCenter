package com.example.vetrazcenter.presentation.courses.course

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.vetrazcenter.data.remote.model.student.StudentInfo
import com.example.vetrazcenter.databinding.FragmentApplyDialogBinding
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import java.util.*


class ApplyDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentApplyDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApplyDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return bottomSheetDialogCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        setupDatePicker()
        setupApplyButtonClickListener(view)

    }

    private fun setupApplyButtonClickListener(view: View) {
        binding.btnApply.setOnClickListener {
            if (binding.etPhone.text.isNullOrEmpty() ||
                binding.etName.text.isNullOrEmpty() ||
                binding.etSurname.text.isNullOrEmpty() ||
                binding.etEmail.text.isNullOrEmpty() ||
                binding.tvBirthday.text.isNullOrEmpty() ||
                binding.etAdress.text.isNullOrEmpty() ||
                binding.etSchool.text.isNullOrEmpty()
            ) {
                showSnackBar(
                    view,
                    context?.getString(com.example.vetrazcenter.R.string.all_fields_must_be_fill)
                        .toString()
                )

            } else {
                val studentInfo = StudentInfo(
                    name = binding.etPhone.text.toString(),
                    surname = binding.etSurname.text.toString(),
                    contactPhone = binding.etPhone.text.toString(),
                    email = binding.etEmail.text.toString(),
                    birthday = binding.tvBirthday.text.toString(),
                    address = binding.etAdress.text.toString(),
                    schoolName = binding.etSchool.text.toString()
                )
                setFragmentResult(
                    "request_key",
                    bundleOf("extra_key" to studentInfo)
                )
                dismiss()
            }
        }
    }


    private fun bottomSheetDialogCreate(): BottomSheetDialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }


    private fun setupDatePicker() {
        val today = Calendar.getInstance()
        binding.datePicker.init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)

        ) { _, year, monthOfYear, dayOfMonth ->
            val month = monthOfYear + 1
            val msg = "$dayOfMonth/$month/$year"
            binding.tvBirthday.text = msg
        }
    }

    private fun showSnackBar(view: View, msg: String) {
        Snackbar.make(
            view,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}