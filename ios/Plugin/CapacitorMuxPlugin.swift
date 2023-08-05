import Foundation
import Capacitor
import MuxUploadSDK

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorMuxPlugin)
public class CapacitorMuxPlugin: CAPPlugin {
    private let implementation = CapacitorMux()
    
    @objc func uploadVideo(_ call: CAPPluginCall) {
        guard let uploadUri = call.options["uploadUri"] as? String else {
          call.reject("Must provide uploadUri")
          return
        }
        
        guard let videoFile = call.options["videoFile"] as? String else {
          call.reject("Must provide videoFile")
          return
        }
        
        // Convert videoFile string to URL
        guard let videoFileURL = URL(string: videoFile) else {
            call.reject("Invalid videoFile URL")
            return
        }
        // Convert uploadUri string to URL
        guard let uploadURL = URL(string: uploadUri) else {
            call.reject("Invalid uploadUri URL")
            return
        }

        
        // Construct custom upload options to upload a file in 6MB chunks
        let chunkSizeInBytes = 6 * 1024 * 1024
        let options = DirectUploadOptions(
          chunkSizeInBytes: chunkSizeInBytes
        )

        let directUpload = DirectUpload(
          uploadURL: uploadURL,
          inputFileURL: videoFileURL,
          options: options
        )

        directUpload.resultHandler = { result in
            switch result {
            case .success:
                CAPLog.print("Video upload success!")
                call.resolve([
                    "success": true
                ])
            case .failure(let error):
                CAPLog.print("!! Video error: \(error.localizedDescription)")
                call.reject(error.localizedDescription, nil, error)
            }
        }

        directUpload.start()
    }
}
