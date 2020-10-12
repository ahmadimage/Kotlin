package com.example.fragmenttask.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fragmenttask.R
import com.example.fragmenttask.base.MyDataBindingComponent
import com.example.fragmenttask.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()
    val dataBindingComponent = MyDataBindingComponent(this)
    private lateinit var binding: FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            null,
            false,
            dataBindingComponent
        )
        binding.result = args.device

        var btn_Click: Button = binding.btnClick

        btn_Click.setOnClickListener {
            val direction =
                DetailFragmentDirections.actionDetailFragmentToNotiFragment()
            findNavController().navigate(direction)
        }


        return binding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = v.findViewById(R.id.imageView)
        val imageUrl: String = args.device.imageUrl.toString()

        txt_name.text = args.device.name.toString()
        txt_brand.text = args.device.brand.toString()
        txt_starting_price.text = args.device.startingPrice.toString()
        txt_smile_points.text = args.device.startingPoints.toString()
        txt_vat_percent.text = args.device.vatPercentage.toString()

        val requestOption = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(context!!).applyDefaultRequestOptions(requestOption)
            .load(imageUrl).into(imageView)

        (activity as MainActivity).text = "abc"

        btn_Click.setOnClickListener {
            val direction =
                DetailFragmentDirections.actionDetailFragmentToNotiFragment()
            findNavController().navigate(direction)
        }

    }
*/
    /*override fun onStop() {
        super.onStop()
        fragmentTransaction.addToBackStack()
    }*/


}
