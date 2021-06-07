package com.example.javas.ui.symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentSymptomFourBinding
import com.example.javas.ui.choosedatevaccine.ChooseDateVaccineViewModel
import com.example.javas.utils.ViewModelFactory
import com.google.firebase.ml.modeldownloader.CustomModel
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions
import com.google.firebase.ml.modeldownloader.DownloadType
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader
import org.tensorflow.lite.Interpreter
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.log


class SymptomFourFragment : Fragment() {

    private lateinit var _binding: FragmentSymptomFourBinding
    private val binding get() = _binding
    private lateinit var viewModel: SymptomViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSymptomFourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[SymptomViewModel::class.java]


        val backPressed = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).backpressed
        val sakitPernapasan = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).sakitPernapasan
        val demam = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).demam
        val batukKering = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).batukKering
        val sakitTenggorokan = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).sakitTenggorokan
        val flu = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).sedangFlu
        val asma = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).asma
        val penyakitParu = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).penyakitParu
        val sakitKepala = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).sakitKepala
        val sakitJantung = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).sakitJantung
        val diabetes = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).diabetes
        val hiperTensi = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).hiperTensi
        val kecapean = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).kecapean
        val gangguanPencernaan = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).gangguanPencernaan
        val baruBepergian = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).baruBepergian
        val bertemuPasienCovid = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).bertemuPasienCovid
        val mengikutiAcara = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).mengikutiAcaraBesar
        val bertemuOrangBanyak = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).bertemuOrangBanyak
        val anggotaKeluargaBekerja = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).anggotaKeluargaBekerja
        val memakaiMasker = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).memakaiMasker
        val menggunakanSanitizer = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).menggunakanSanitizer

        if (backPressed){
            if (mengikutiAcara) {
                binding.rbYes1.isChecked = true
            } else {
                binding.rbNo1.isChecked = true
            }

            if (bertemuOrangBanyak) {
                binding.rbYes2.isChecked = true
            } else {
                binding.rbYes2.isChecked = true
            }

            if (anggotaKeluargaBekerja) {
                binding.rbYes3.isChecked = true
            } else {
                binding.rbNo3.isChecked = true
            }

            if (memakaiMasker) {
                binding.rbYes4.isChecked = true
            } else {
                binding.rbNo4.isChecked = true
            }

            if (menggunakanSanitizer) {
                binding.rbYes5.isChecked = true
            } else {
                binding.rbNo5.isChecked = true
            }
        }

        binding.btnNextResult.setOnClickListener {
            val name = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).name
            val toResult = SymptomFourFragmentDirections.actionSymptomFourFragmentToResultNegativeFragment()
            toResult.name=name

            val toPositive = SymptomFourFragmentDirections.actionSymptomFourFragmentToResultPositiveFragment()
            toPositive.name=name

            var status: Boolean

            //berikan machine learning disini dan ubah status dari false menjadi sesuai machine learning

            fun demam(): Float = if (demam) 1f else 0f
            fun sakitPernapasan(): Float = if (sakitPernapasan) 1f else 0f
            fun batukKering(): Float = if (batukKering) 1f else 0f
            fun sakitTenggorokan(): Float = if (sakitTenggorokan) 1f else 0f
            fun flu(): Float = if (flu) 1f else 0f
            fun asma(): Float = if (asma) 1f else 0f
            fun penyakitParu(): Float = if (penyakitParu) 1f else 0f
            fun sakitKepala(): Float = if (sakitKepala) 1f else 0f
            fun sakitJantung(): Float = if (sakitJantung) 1f else 0f
            fun diabetes(): Float = if (diabetes) 1f else 0f
            fun hipertensi(): Float = if (hiperTensi) 1f else 0f
            fun kecapean(): Float = if (kecapean) 1f else 0f
            fun gangguanPencernaan(): Float = if (gangguanPencernaan) 1f else 0f
            fun baruBepergian(): Float = if (baruBepergian) 1f else 0f
            fun bertemuPasienCovid(): Float = if (bertemuPasienCovid) 1f else 0f
            fun mengikutiAcara(): Float = if (mengikutiAcara) 1f else 0f
            fun bertemuOrangBanyak(): Float = if (bertemuOrangBanyak) 1f else 0f
            fun anggotaKeluargaBekerja(): Float = if (anggotaKeluargaBekerja) 1f else 0f


            val conditions = CustomModelDownloadConditions.Builder()
                .requireWifi()
                .build()
            FirebaseModelDownloader.getInstance()
                .getModel("javas", DownloadType.LOCAL_MODEL, conditions)
                .addOnCompleteListener {
                    // Download complete. Depending on your app, you could enable the ML
                    // feature, or switch from the local model to the remote model, etc.

                        customModel ->
                    customModel
                        .addOnSuccessListener { model: CustomModel? ->
                            val modelFile = model?.file
                            if (modelFile != null) {
                            }
                            val interpreter:Interpreter = modelFile?.let { Interpreter(it) }!!

                            val input = ByteBuffer.allocateDirect(18*4).order(ByteOrder.nativeOrder())
                            input.putFloat(demam())
                            input.putFloat(sakitPernapasan())
                            input.putFloat(batukKering())
                            input.putFloat(sakitTenggorokan())
                            input.putFloat(flu())
                            input.putFloat(asma())
                            input.putFloat(penyakitParu())
                            input.putFloat(sakitKepala())
                            input.putFloat(sakitJantung())
                            input.putFloat(diabetes())
                            input.putFloat(hipertensi())
                            input.putFloat(kecapean())
                            input.putFloat(gangguanPencernaan())
                            input.putFloat(baruBepergian())
                            input.putFloat(bertemuPasienCovid())
                            input.putFloat(mengikutiAcara())
                            input.putFloat(bertemuOrangBanyak())
                            input.putFloat(anggotaKeluargaBekerja())



//                            input.putFloat(1f)
//                            input.putFloat(1f)
//                            input.putFloat(1f)
//                            input.putFloat(1f)
//                            input.putFloat(0f)
//                            input.putFloat(1f)
//                            input.putFloat(1f)
//                            input.putFloat(1f)
//                            input.putFloat(0f)
//                            input.putFloat(0f)
//                            input.putFloat(0f)
//                            input.putFloat(1f)
//                            input.putFloat(0f)
//                        input.putFloat(0f)
//                        input.putFloat(0f)
//                        input.putFloat(0f)
//                        input.putFloat(1f)
//                        input.putFloat(1f)

                            val modelOutput = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder())
                            interpreter.run(input, modelOutput)


                            modelOutput.rewind()
                            val probabilities = modelOutput.asFloatBuffer()
                            try {
                                val check = probabilities.get(0)

                                if (check >= 0.8f){
                                    status=true
                                    if (validate()){
                                        if (status){
                                            val symptom = hashMapOf(
                                                "covid-19" to status
                                            )
                                            viewModel.getUser(name)
                                                .collection("vaccination")
                                                .document("symptom")
                                                .set(symptom)
                                            
                                            view.findNavController().navigate(toPositive)
                                        }
                                    }
                                }
                                else{
                                    status=false
                                    val symptom = hashMapOf(
                                        "covid-19" to status
                                    )
                                    viewModel.getUser(name)
                                        .collection("vaccination")
                                        .document("symptom")
                                        .set(symptom)
                                    Toast.makeText(context, "Anda tidak terindikasi covid-19", Toast.LENGTH_SHORT).show()
                                    view.findNavController().navigate(toResult)
                                }
                            }catch (e: IOException) {
                                Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show()
                            }

                        }
                }



//            if (validate()){
//                if (status){
//                    view.findNavController().navigate(toPositive)
//                }else{
//                    Toast.makeText(context, "Anda tidak terindikasi covid-19", Toast.LENGTH_SHORT).show()
//                    view.findNavController().navigate(toResult)
//                }
//            }else{
//                Toast.makeText(context, "Mohon jawab semua pertanyaan", Toast.LENGTH_SHORT).show()
//            }
        }
    }


    private fun validate(): Boolean {
        var valid = true
        if (binding.rgMengikuti.checkedRadioButtonId == -1
            || binding.rgAnggota.checkedRadioButtonId == -1
            || binding.rgBertemu.checkedRadioButtonId == -1
            || binding.rgHand.checkedRadioButtonId == -1
            || binding.rgMasker.checkedRadioButtonId == -1) {

            valid = false
        }
        return valid
    }

}