package com.example.javas.ui.symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.javas.databinding.FragmentSymptomFourBinding
import com.example.javas.ml.Javas
import com.example.javas.ml.Javasfix
import com.example.javas.ml.Javasmodel
import com.example.javas.ml.Javasmodelsymptom
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.TensorProcessor
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer


class SymptomFourFragment : Fragment() {

    private lateinit var _binding: FragmentSymptomFourBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSymptomFourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
//            val model = context?.let { it1 -> Javasmodel.newInstance(it1) }
//
//            val modela = context?.let { it1 -> Javas.newInstance(it1) }

//            val modela = context?.let { it1 -> Javasmodelsymptom.newInstance(it1) }

            val model = context?.let { it1 -> Javasfix.newInstance(it1) }


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

            val byteBuffer : ByteBuffer = ByteBuffer.allocateDirect(18*4)
            byteBuffer.putFloat(demam())
            byteBuffer.putFloat(sakitPernapasan())
            byteBuffer.putFloat(batukKering())
            byteBuffer.putFloat(sakitTenggorokan())
            byteBuffer.putFloat(flu())
            byteBuffer.putFloat(asma())
            byteBuffer.putFloat(penyakitParu())
            byteBuffer.putFloat(sakitKepala())
            byteBuffer.putFloat(sakitJantung())
            byteBuffer.putFloat(diabetes())
            byteBuffer.putFloat(hipertensi())
            byteBuffer.putFloat(kecapean())
            byteBuffer.putFloat(gangguanPencernaan())
            byteBuffer.putFloat(baruBepergian())
            byteBuffer.putFloat(bertemuPasienCovid())
            byteBuffer.putFloat(mengikutiAcara())
            byteBuffer.putFloat(bertemuOrangBanyak())
            byteBuffer.putFloat(anggotaKeluargaBekerja())





            val array = byteArrayOf(4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4)
//            demam(), sakitPernapasan(), batukKering(),sakitTenggorokan()
//            ,flu(),asma(),penyakitParu(),sakitKepala(),sakitJantung(),diabetes()
//            ,hipertensi(),kecapean(),gangguanPencernaan(),baruBepergian(),bertemuPasienCovid(),mengikutiAcara(),bertemuOrangBanyak()
//            ,anggotaKeluargaBekerja()
              //val byteBuffer = ByteBuffer.wrap(array)

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 18), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model?.process(inputFeature0)
            val outputFeature0 = outputs?.outputFeature0AsTensorBuffer

            val result = outputFeature0?.floatArray?.get(0)
            if (result != null) {
                Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
//                if (result < 0.5) {
//                    Toast.makeText(context, "mantap", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(context, "koplo", Toast.LENGTH_SHORT).show()
//                }
            }
//            val tensorLabel =
//                outputFeature0?.let { it1 ->
//                    TensorLabel(
//                        arrayListOf("yes", "no"),
//                        it1
//                    )
//                }
//
//            val probability = tensorLabel?.mapWithFloatValue?.get("yes")
//            probability?.let {
//                if (it > 0.80) {
//                    Toast.makeText(context, "mantappp", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(context, "gebleg", Toast.LENGTH_SHORT).show()
//                }
//            }


//            val name = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).name
//            val toResult = SymptomFourFragmentDirections.actionSymptomFourFragmentToChooseDateVaccineFragment()
//            toResult.name=name
            if (model != null) {
                model.close()
            }
//            view.findNavController().navigate(toResult)
        }

    }


}